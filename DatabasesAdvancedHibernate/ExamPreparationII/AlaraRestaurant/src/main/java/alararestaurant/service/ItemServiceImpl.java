package alararestaurant.service;

import alararestaurant.domain.dtos.ItemImportDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static alararestaurant.constants.Constants.INCORRECT_DATA_MESSAGE;

@Service
public class ItemServiceImpl implements ItemService {
    private final static String ITEMS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\items.json";

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean itemsAreImported() {
        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
        return this.fileUtil.readFile(ITEMS_FILE_PATH);
    }

    @Override
    public String importItems(String items) {
        StringBuilder result = new StringBuilder();
        ItemImportDto[] itemImportDtos = this.gson.fromJson(items, ItemImportDto[].class);

        for (ItemImportDto itemImportDto: itemImportDtos) {
            Item item = this.itemRepository.findByName(itemImportDto.getName());

            if (item != null) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            item = this.modelMapper.map(itemImportDto, Item.class);

            if (!this.validationUtil.isValid(item)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Category category = this.categoryRepository.findByName(itemImportDto.getCategory());

            if (category == null) {
                category = new Category();
                category.setName(itemImportDto.getCategory());

                if (!this.validationUtil.isValid(category)) {
                    result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }
                this.categoryRepository.saveAndFlush(category);
            }

            item.setCategory(category);
            this.itemRepository.saveAndFlush(item);

            result.append(String.format("Record %s successfully imported.", item.getName()))
                    .append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
