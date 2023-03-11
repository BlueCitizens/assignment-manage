# assignment-manage
基于springboot的收作业工具

springboot整合Mybatis，实现工作部署和任务提交。

核心功能：
  - 批量导入成员用户。
  - 增加作业。
  - 成员用户提交作业。
  - 下载单项作业压缩包。
  - 可手动分配管理权限。

**部署时请修改customs.properties中的rootPath为自己准备存储实际文件的路径！！！**

**项目的用户密码使用明文存储，请谨慎使用或自行实现加密策略！！！**
  
2020/2/3 初步整合了Spring Security框架实现带权限的拦截器。

2020/5/28 第一个稳定可用的版本，优化文件逻辑和权限分支。

    增加或修改了以下逻辑：
    
    已提交的文件再次提交将覆盖同一任务前一次上传的文件（无视文件名）。若同名文件属于他人则返回冲突信息。
    
    附加相应权限的用户可以查看磁盘上任务对应文件路径下的所有文件，并打包下载该任务所有文件。
    
    已登录用户可查看当前用户单个任务的提交历史和所有提交历史。
    
    前端暂时没有权限控制。暂时只能对照url修改数据库permission以及关联表项

2023/3/10 修复了新建的作业第一次提交出错的问题；清理不必要的脚本和样式；此项目终结并停止维护。

## 已发现问题
使用MySQL5.7以及更高版本时可能会报如下错误

```
SELECT list is not in GROUP BY clause and contains nonaggregated column
```

这是因为MySQL新增了对规范ONLY_FULL_GROUP_BY的默认支持，而我违背该规范使用了group by所导致。由于我已无力完善该项目，请参考网络上的教程解决该问题，这里提供大致的解决方案。

修改配置文件，windows下修改my.ini，Linux下修改my.cnf

将```[mysqld]```下```sql_mode```参数的```ONLY_FULL_GROUP_BY```删除，如果没有就新增该参数，并在等号后填上```STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION```

## 其他
由于我在构建该项目时经验尚浅，大量不规范的代码存在并且缺乏必要注释，因此它可能并不适合作为学习的案例。

  ![stulist](https://github.com/BlueCitizens/work-manage/blob/master/screenshots/stulist.gif)
  ![stulist](https://github.com/BlueCitizens/work-manage/blob/master/screenshots/uploadwork.gif)
  ![stulist](https://github.com/BlueCitizens/work-manage/blob/master/screenshots/worklist.gif)
