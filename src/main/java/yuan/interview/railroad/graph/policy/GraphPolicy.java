package yuan.interview.railroad.graph.policy;

import java.util.Map;

import yuan.interview.railroad.graph.algorithm.MinPath;
import yuan.interview.railroad.graph.base.Graph;
import yuan.interview.railroad.graph.base.GraphReader;

/** 
 * @ClassName: GraphPolicy
 * @Description:  图策略,
 * 						{@link Graph}只是提供了图数据的结构模式.包括表示顶点,顶点之间的有向边.及边上的多维度的权重值.
 * 						要表示具体的地图业务,需要在{@link Graph}基础上规范权重的维度信息.
 * 						比如在某个地图上,两站点的路程保存在有向边的DIST维度的权重分量.两站点间跨越站数保存在STOP维度的分量,该分量值一般为1.
 * 						这样就可以计算路程最短的路线,指定基于DIST维度进行权重计算即可,或者搜索跨越站点数满足一定的数值区间,指定基于STOP维度进行权重计算即可
 * 						以上关于指定DIST和STOP两个权重维度来存储应用于相应业务的地图数据,就是图策略.
 * 
 * 						图策略将会影响两个方面的内容:
 * 						1	怎么从外界数据读取解析得到完整的图信息.比如存储文件上只保存各节点间路程,如果根据以上DIST和STOP的图策略,
 * 							将路程值保存为DIST维度上的权重分量值,另外在STOP维度上权重值全设置成1,表示每条边跨越站数是1.
 * 							有些地图可能还记录站点之间的路程耗时,可以再有一个TIME权重维度,
 * 							将外界地图数据,按照一定逻辑输入到{@link Graph}里,且自动补充成完整数据,需要在具体的图策略子类里提供实现.
 * 							所以图策略子类应该为泛型类 W 现实具体的枚举类,以规范具有哪些维度的权重分量.就相应的数据输入逻辑.
 * 
 * 						2	根据1存储的完整图数据,以及graph.algorithm提供的算法类.图策略子类进行适当的业务封装.并以命令模式的具体命令子类对外提供业务支持.
 * 							比如将最短路现算法类{@link MinPath}封装实现成最短耗时,或最短路程线路的检索命令子类.
 * 							注意:图策略子类最好也提供帮助手册命令.
 * 							所以图策略子类应该明确指定泛型类 C,以确定将以哪些命令类的子类对外提供具体命令集.
 * 
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-上午6:20:42
 */
public interface GraphPolicy<C, W extends Enum<W>> {
	
	/** 设置图数据导入器,(设置好后,自动加载) */
	public void setGraphReader(GraphReader graphReader);
	
	/** 具体图策略子类提供的命令集 */
	public Map<String, C> getCommands();

}
