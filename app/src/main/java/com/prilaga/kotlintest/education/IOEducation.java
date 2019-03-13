package com.prilaga.kotlintest.education;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Oleg Tarashkevich on 13/01/2019.
 */
public class IOEducation {

    public void init(){
        testFile();
    }

    public void testFile(){
        File file = new File(".");
        File[] files1 = file.listFiles();
        File[] files2 = file.listFiles(new Filter("png,jpg"));
    }

    public class Filter implements FileFilter{

        String[] ext;

        Filter(String ext)
        {
            this.ext = ext.split(",");
        }
        private String getExtension(File pathname)
        {
            String filename = pathname.getPath();
            int i = filename.lastIndexOf('.');
            if ((i > 0) && (i < filename.length()-1)) {
                return filename.substring(i+1).toLowerCase();
            }
            return "";
        }

        @Override public boolean accept(File pathname) {
            return false;
        }
    }
}
