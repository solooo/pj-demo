package data;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/26
 */
public class DataBean {
    private String orderid;
    private String quyu;
    private String shengfen;
    private String chengshi;
    private String chanpinliebie;
    private String yushufangshi;
    private Integer dingdanshuliang;
    private Float danjia;
    private Double xiaoshoue;
    private String createtime;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getQuyu() {
        return quyu;
    }

    public void setQuyu(String quyu) {
        this.quyu = quyu;
    }

    public String getShengfen() {
        return shengfen;
    }

    public void setShengfen(String shengfen) {
        this.shengfen = shengfen;
    }

    public String getChengshi() {
        return chengshi;
    }

    public void setChengshi(String chengshi) {
        this.chengshi = chengshi;
    }

    public String getChanpinliebie() {
        return chanpinliebie;
    }

    public void setChanpinliebie(String chanpinliebie) {
        this.chanpinliebie = chanpinliebie;
    }

    public String getYushufangshi() {
        return yushufangshi;
    }

    public void setYushufangshi(String yushufangshi) {
        this.yushufangshi = yushufangshi;
    }

    public Integer getDingdanshuliang() {
        return dingdanshuliang;
    }

    public void setDingdanshuliang(Integer dingdanshuliang) {
        this.dingdanshuliang = dingdanshuliang;
    }

    public Float getDanjia() {
        return danjia;
    }

    public void setDanjia(Float danjia) {
        this.danjia = danjia;
    }

    public Double getXiaoshoue() {
        return xiaoshoue;
    }

    public void setXiaoshoue(Double xiaoshoue) {
        this.xiaoshoue = xiaoshoue;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(orderid).append(",");
        sb.append(quyu).append(",");
        sb.append(shengfen).append(",");
        sb.append(chengshi).append(",");
        sb.append(chanpinliebie).append(",");
        sb.append(yushufangshi).append(",");
        sb.append(dingdanshuliang).append(",");
        sb.append(danjia).append(",");
        sb.append(xiaoshoue).append(",");
        sb.append(createtime);
        return sb.toString();
    }
}
