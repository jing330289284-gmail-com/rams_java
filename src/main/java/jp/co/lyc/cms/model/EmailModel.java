package jp.co.lyc.cms.model;

public class EmailModel {
	
    private static final long serialVersionUID = 1L;
    //发送者的邮箱账号
    private String userName;
    //发送者的密码
    private String password;
    //发送者的邮箱地址
    private String fromAddress;
    //接收者的邮箱地址
    private String toAddress;
    //设置邮件主题
    private String subject;
    //设置邮件内容
    private String context;
    //设置邮件类型
    private String contextType;
    // fileのパース
    private String resumePath;
    //　メール内容
    private String mailConfirmContont;
    //　メールTO
    private String selectedmail;
    //　メールCC
    private String[] selectedMailCC;
 //　メールFROM
    private String mailFrom;
    
    private String mailTitle;
    
    private String resumeName;
    
    
    

    
    
    
    
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getMailTitle() {
		return mailTitle;
	}
	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public String getResumePath() {
		return resumePath;
	}
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}
	public String getMailConfirmContont() {
		return mailConfirmContont;
	}
	public void setMailConfirmContont(String mailConfirmContont) {
		this.mailConfirmContont = mailConfirmContont;
	}
	public String getSelectedmail() {
		return selectedmail;
	}
	public void setSelectedmail(String selectedmail) {
		this.selectedmail = selectedmail;
	}
	public String[] getSelectedMailCC() {
		return selectedMailCC;
	}
	public void setSelectedMailCC(String[] selectedMailCC) {
		this.selectedMailCC = selectedMailCC;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getContextType() {
		return contextType;
	}
	public void setContextType(String contextType) {
		this.contextType = contextType;
	}
    
    
}
