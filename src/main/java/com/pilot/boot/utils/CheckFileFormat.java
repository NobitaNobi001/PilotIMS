package com.pilot.boot.utils;

import com.pilot.boot.exception.MyException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ezuy
 * @date 21/4/12 17:21
 */
public class CheckFileFormat {


    private static final String EXCEL_FILE_FORMAT = ".xls";
    private static final String STL_FILE_FORMAT = ".stl";

    /**
     * check excel file format
     * @param file
     */
    public static void checkExcelFile(MultipartFile file) {

        //1.check file == null
        checkFileSize(file);

        //2.get file suffix
        int begin = file.getOriginalFilename().indexOf(".");
        String suffix = file.getOriginalFilename().substring(begin);

        //3.check file format
        if (!EXCEL_FILE_FORMAT.equals(suffix)) {
            throw  new MyException(CommonResult.fail(100,"请上传xls格式的文件"));
        }
    }

    /**
     * check stl file format
     * @param file
     */
    public static void checkStlFile(MultipartFile file){
        //1.check file == null
        checkFileSize(file);

        //2.get file suffix
        int begin = file.getOriginalFilename().indexOf(".");
        String suffix = file.getOriginalFilename().substring(begin);

        //3.check file format
        if (!STL_FILE_FORMAT.equals(suffix)) {
            throw  new MyException(CommonResult.fail(100,"请上传stl格式的文件"));
        }
    }

    public static void checkFileSize(MultipartFile file){

        if (file.getSize() == 0) {
            throw new MyException(CommonResult.fail(100, "请选择需要上传的文件"));
        }
    }
}
