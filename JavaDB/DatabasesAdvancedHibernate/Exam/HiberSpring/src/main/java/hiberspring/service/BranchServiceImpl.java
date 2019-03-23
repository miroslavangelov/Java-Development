package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.domain.dtos.BranchImportDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class BranchServiceImpl implements BranchService {
    private final static String BRANCHES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\branches.json";

    private final BranchRepository branchRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository, ModelMapper modelMapper, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return this.fileUtil.readFile(BRANCHES_FILE_PATH);
    }

    @Override
    public String importBranches(String branchesFileContent) {
        StringBuilder result = new StringBuilder();
        BranchImportDto[] branchImportDtos = this.gson.fromJson(branchesFileContent, BranchImportDto[].class);

        for (BranchImportDto branchImportDto: branchImportDtos) {
            Town town = this.townRepository.findByName(branchImportDto.getTown());

            if (town == null) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Branch branch = this.modelMapper.map(branchImportDto, Branch.class);
            branch.setTown(town);

            if (!this.validationUtil.isValid(branch)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            this.branchRepository.saveAndFlush(branch);
            result.append(String.format(
                    SUCCESSFUL_IMPORT_MESSAGE,
                    branch.getClass().getSimpleName(),
                    branch.getName())
            ).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
