# LogMaker
## 随机日志生成

### 传参说明

#### 不传参

默认生成100条日志，5秒生成一次

#### 传一个参数x

生成x条日志，每10秒生成一次

#### 传两个参数x y

生成x条日志，每y秒生成一次

#### 自定义生成可以在main方法中调整

com.orange.App.main

### 代码说明

该工具主要用于自身学习，模拟生成计算机运载日志，后期将改进数据真实性问题



### CentOS path

```shell
 cd /export/logs/log_maker
```

### Linux命令

#### Hadoop启动

```shell
第一台机器执行以下命令
cd  /export/servers/hadoop-2.7.5/
sbin/start-dfs.sh
sbin/start-yarn.sh
sbin/mr-jobhistory-daemon.sh start historyserver
```

启动metaStore

#### Hadoop Web页面

##### 查看hdfs

http://node01:50070/explorer.html 

##### 查看yarn集群

http://node01:8088/cluster 

##### 查看历史完成的任务

http://node01:19888/jobhistory

#### Hive

##### Hive启动

```shell
cd /export/servers/apache-hive-2.1.1-bin/
bin/hive
```

###### Hive启动metastore服务（遇到问题时可以尝试）

```shell
hive --service metastore
```

##### 启动hiveserver2服务

###### 前台启动

```shell
cd /export/servers/apache-hive-2.1.1-bin/
bin/hive --service hiveserver2
```

###### 后台启动

```shell
nohup bin/hive --service hiveserver2  > /dev/null 2>&1 &
```

###### 使用beeline连接hiveserver2

输入用户名和密码,用户名必须为root,密码任意.

```shell
bin/beeline
beeline> !connect jdbc:hive2://node03:10000
```

![image-20200419164659404](src\logMaker\README\image-20200419164659404.png)

