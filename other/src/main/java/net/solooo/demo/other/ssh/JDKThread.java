package net.solooo.demo.other.ssh;

import com.jcraft.jsch.Session;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;

public class JDKThread extends Thread {
	static Logger log4j = Logger.getLogger(JDKThread.class.getClass());

	CountDownLatch latch;
	Session session;

	private String host;
	private String user;
	private String passwd;

	public JDKThread(String hostLine, CountDownLatch latch) {
		String[] items = hostLine.split(" ");
		this.host = items[0];
		this.user = items[1];
		this.passwd = items[2];
		this.latch = latch;
	}

	public void run() {
		try {
			// 0. 检查连通性
			detectConnection();

			// 1 安装jdk
			detectJDK();			

		} catch (Exception e) {			
			log4j.error(e.getMessage());
		} finally{			
			latch.countDown();	// 完成工作，计数器减一
			session.disconnect();
		}
	}

	/// 检测连通性 ///////////////////////////
	private void detectConnection() throws Exception {
		session = JSSHUtil.connect(host, user, passwd);
		
		System.out.println(host  + " " + user + " " + passwd);
	}

	/// 检测jdk "jdk1.7.0_80" ///////////////////////////////////////
	private void detectJDK() throws Exception {
		String homeDir = "/home/ht";
		
		String command1 = "cd /home ; mkdir -p " + homeDir;		
		String command2 = "cd /opt/jdk ; ./bin/java -version";
		String command3 = "cd /home/ht ; tar zxvf jdk-7u80-linux-x64.tar.gz -C /opt";
		String command4 = "cd /opt ; ln -s jdk1.7.0_80 jdk";
		String command5 = "source /etc/profile";
				
		boolean isDir = JSSHUtil.sshDir(session, homeDir);	
		if(isDir == false){
			// 强制新建
			String rs = JSSHUtil.execCmd(session, command1);
			log4j.info(host +  " 已新建 " + homeDir + "\n" + rs);
		}

		// 正式安装
		String rs = JSSHUtil.execCmdExt(session, command2);

		if (rs != null && rs.contains("java version")) {
			JSSHUtil.sshSftp(session, "/etc", "doc/java/profile");
			rs = JSSHUtil.execCmdExt(session, command4);

			log4j.info(host + " jdk 已运行 ");
		} else {
			// copy 并覆盖
			JSSHUtil.sshSftp(session, homeDir, "doc/java/jdk-7u80-linux-x64.tar.gz");
			rs = JSSHUtil.execCmd(session, command3);
			if (rs.contains("jdk1.7.0_80")) {
				log4j.info(host + " 解压成功 jdk ");
			} else {
				throw new Exception(host + "解压失败 jdk ");
			}

			JSSHUtil.sshSftp(session , "/etc", "doc/java/profile");
			rs = JSSHUtil.execCmdExt(session, command4);
			log4j.info(host + " 软链接成功 \n" + rs);

			rs = JSSHUtil.execCmdExt(session, command5);
			log4j.info(host + " java 环境生效 \n" + rs);
		}
	}
}
