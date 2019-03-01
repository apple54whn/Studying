# Git—分布式版本控制系统

![1550239093558](images/1550239093558.png)

[Git安装](https://git-scm.com/downloads)后需要设置name和email，也可以直接从[Github](https://desktop.github.com/)下载卓面版，会自动下载安装好Git



Git和其他版本控制系统如SVN的一个不同之处就是有暂存区的概念。
什么是工作区（Working Directory）？
工作区就是你在电脑里能看到的目录，比如我的reporstory文件夹就是一个工作区。
有的同学可能会说repository不是版本库吗怎么是工作区了？其实repository目录是工作区，在这个目录中的“.git”隐藏文件夹才是版本库。这回概念清晰了吧。
Git的版本库里存了很多东西，其中最重要的就是称为stage（或者叫index）的暂存区，还有Git为我们自动创建的第一个分支master，以及指向master的一个指针叫HEAD。
如下图所示：

![1550240152050](images/1550240152050.png)

分支和HEAD的概念我们稍后再讲。前面讲了我们把文件往Git版本库里添加的时候，是分两步执行的：

第一步是用git add把文件添加进去，实际上就是把文件修改添加到暂存区；

第二步是用git commit提交更改，实际上就是把暂存区的所有内容提交到当前分支。

因为我们创建Git版本库时，Git自动为我们创建了唯一一个master分支，所以现在，git commit就是往master分支上提交更改。
你可以简单理解为，需要提交的文件修改通通放到暂存区，然后，一次性提交暂存区的所有修改。

