package com.hjz.filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MyFilter extends FileFilter{
    private String[] filterString = null;
    public MyFilter(String[] filStrings){
        this.filterString = filStrings;
    }
    public boolean accept(File file){
           if(file.isDirectory()) return true;
           for(int i=0; i<filterString.length; ++i)
               if(file.getName().endsWith(filterString[i]))
                   return true;
           /* 返回要显示的文件类型 */
           /*
            *   File.isDirectory()测试此抽象路径名表示的文件是否是一个目录
           */
           return false;
      }
      
      public String getDescription() {
          String ss = "";
          for(int i=0; i<filterString.length; ++i)
              ss += " *" + filterString[i];
          return("Txt Files(" + ss + ")");                  //返回显示文件类型的描述
      }
}
