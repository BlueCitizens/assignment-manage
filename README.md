# work-manage
基于springboot的收作业工具
 springboot整合Mybatis，实现工作部署和任务提交。前后端分离。第一次commit只实现了基本功能，未关注权限分离和安全漏洞。和预期系统差很多，有空慢慢更新。

  - 批量导入成员用户。
  - 增加作业。
  - 成员用户提交作业。
  - 下载单项作业压缩包。
  
  部署时请修改customs.properties中的rootPath为自己的文件路径
  
2020/2/3 初步整合了Spring Security框架实现带权限的拦截器

2020/5/28 第一个稳定可用的版本，优化文件逻辑和权限分支。

增加或修改了以下逻辑：
已提交的文件再次提交将覆盖同一任务前一次上传的文件（无视文件名）。若同名文件属于他人则返回冲突信息。
附加相应权限的用户可以查看磁盘上任务对应文件路径下的所有文件，并打包下载该任务所有文件。
已登录用户可查看当前用户单个任务的提交历史和所有提交历史。
前端暂时没有权限控制。暂时只能对照url修改数据库permission以及关联表项
  
  ![stulist](https://github.com/BlueCitizens/work-manage/blob/master/screenshots/stulist.gif)
  ![stulist](https://github.com/BlueCitizens/work-manage/blob/master/screenshots/uploadwork.gif)
  ![stulist](https://github.com/BlueCitizens/work-manage/blob/master/screenshots/worklist.gif)
