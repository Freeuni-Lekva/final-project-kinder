package ge.kinder.Mail;

public  class Mail {


    private String MESSAGE;
    private String SUBJECT;
    private String RECEIVER;


    public Mail(String MESSAGE, String SUBJECT, String RECEIVER) {
        this.MESSAGE = MESSAGE;
        this.SUBJECT = SUBJECT;
        this.RECEIVER = RECEIVER;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getSUBJECT() {
        return SUBJECT;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    public String getRECEIVER() {
        return RECEIVER;
    }

    public void setRECEIVER(String RECEIVER) {
        this.RECEIVER = RECEIVER;
    }
}
