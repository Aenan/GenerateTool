GenerateTool工具用于生成测试数据

注意事项：
1、在config.xml中按一下格式输入PICT路径和xml文件的路径。
<Config>
	<XMLFilePath>D:\Application\PICT\test.xml</XMLFilePath>
	<PICTPath>D:\Application\PICT</PICTPath>	
</Config>
并将config.xml与GenerateTool.exe放置同一目录。

2、xml文件格式为demo.xml中的格式。标签名及属性名不可以改变。

3、若生成数据不正确，可以尝试删除PICT目录下temp.exe、temp.bat、temp.xls