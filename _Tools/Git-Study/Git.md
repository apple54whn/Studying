## 1 安装&配置

* 官网有下载安装教程：https://git-scm.com/downloads

* 全局配置用户名和邮箱！

  ```
  git config --global user.name 'conanan'
  git config --global user.email 'conanan@gmail.com'
  ```

  > `--local`只对某个仓库生效，但必须在Git仓库文件夹中
  >
  > `--system`对系统所有登录的用户生效，没啥用

* 显示配置信息：`git config --list [--global / local / system]`

## 2 init & status & add & commit

* init 新建仓库：

  * 已有项目代码纳入 Git 管理。会在该文件夹下创建`.git`文件夹

    ```
    cd 文件夹
    git init
    ```

  * 新建项目直接用 Git 管理。会在当前目录创建`your_project`文件夹，并在该文件夹中创建`.git`文件夹

    ```
    git init your_project
    ```

  * 在`your_project`目录中配置 local 信息，则会替代 global 配置信息

*  status。查看该**工作目录**中未被 Git 管理的和已被 Git 管理的文件

  ```
  git status
  ```

* add。添加到 Git **暂存区**。后面可以跟**多个文件或文件夹，以空格隔开**；可以使用通配符`*`

  ```
  git add README.md
  ```

  ```
  git add -A #添加所有
  ```

* commit。add 到暂存区后**提交暂存区中的文件**到 Git 仓库，信息间有空格使用双引号包裹

  ```
  git commit -m "first commit"
  ```

* log。每次提交日志信息，有提交的码，作者（name + email），时间，提交信息。

  ```
  git log
  ```

  

## 3 记录每次更新到仓库



