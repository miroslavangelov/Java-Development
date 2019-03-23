package hiberspring.service;

import hiberspring.domain.dtos.ProductImportDto;
import hiberspring.domain.dtos.ProductImportRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final static String PRODUCTS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\products.xml";

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository, ModelMapper modelMapper, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return this.fileUtil.readFile(PRODUCTS_FILE_PATH);
    }

    @Override
    public String importProducts() throws JAXBException, FileNotFoundException {
        StringBuilder result = new StringBuilder();
        ProductImportRootDto productImportRootDto = this.xmlParser
                .parseXml(ProductImportRootDto.class, PRODUCTS_FILE_PATH);

        for (ProductImportDto productImportDto: productImportRootDto.getProductImportDtos()) {
            Branch branch = this.branchRepository.findByName(productImportDto.getBranch());

            if (branch == null) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Product product = this.modelMapper.map(productImportDto, Product.class);
            product.setBranch(branch);

            if (!this.validationUtil.isValid(product)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            this.productRepository.saveAndFlush(product);
            result.append(String.format(
                    SUCCESSFUL_IMPORT_MESSAGE,
                    product.getClass().getSimpleName(),
                    product.getName())
            ).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
