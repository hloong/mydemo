package com.hloong.mydemo.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class ShangHaiPolice {

    /**
     * resultcode : 200
     * reason : Success
     * result : {"title":"上海市政派出所信息","list":[{"name":"文保分局沪东高校派出所","addr":"中山北一路801号","tel":"22027732"},{"name":"崇明县公安局庙镇派出所","addr":"宏海公路1803号","tel":"59361610"},{"name":"崇明县公安局三星派出所","addr":"三星镇海桥公路2号","tel":"59300506"},{"name":"崇明县公安局绿华派出所","addr":"绿华镇新建路634号","tel":"59351635"},{"name":"崇明县公安局新村派出所","addr":"新村乡新中村新跃160号","tel":"59650598"},{"name":"崇明县公安局新海派出所","addr":"新海农场场部北侧","tel":"59655712"},{"name":"崇明县公安局长征派出所","addr":"长征农场派出所生活区长征农场场部","tel":"59311459"},{"name":"崇明县公安局长江派出所","addr":"东风农场林风公路1579号","tel":"59641914"},{"name":"崇明县公安局东旺派出所","addr":"前哨农场前哨公路18号","tel":"59471109"},{"name":"崇明县公安局东滩湿地保护区治安派出所","addr":"陈家镇瀛陈公路崇明县团结沙","tel":"59404611"},{"name":"崇明县公安局长兴派出所","addr":"长兴镇海舸路659号","tel":"56851431"},{"name":"崇明县公安局横沙派出所","addr":"民东路1588号","tel":"24060670"}]}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    /**
     * title : 上海市政派出所信息
     * list : [{"name":"文保分局沪东高校派出所","addr":"中山北一路801号","tel":"22027732"},{"name":"崇明县公安局庙镇派出所","addr":"宏海公路1803号","tel":"59361610"},{"name":"崇明县公安局三星派出所","addr":"三星镇海桥公路2号","tel":"59300506"},{"name":"崇明县公安局绿华派出所","addr":"绿华镇新建路634号","tel":"59351635"},{"name":"崇明县公安局新村派出所","addr":"新村乡新中村新跃160号","tel":"59650598"},{"name":"崇明县公安局新海派出所","addr":"新海农场场部北侧","tel":"59655712"},{"name":"崇明县公安局长征派出所","addr":"长征农场派出所生活区长征农场场部","tel":"59311459"},{"name":"崇明县公安局长江派出所","addr":"东风农场林风公路1579号","tel":"59641914"},{"name":"崇明县公安局东旺派出所","addr":"前哨农场前哨公路18号","tel":"59471109"},{"name":"崇明县公安局东滩湿地保护区治安派出所","addr":"陈家镇瀛陈公路崇明县团结沙","tel":"59404611"},{"name":"崇明县公安局长兴派出所","addr":"长兴镇海舸路659号","tel":"56851431"},{"name":"崇明县公安局横沙派出所","addr":"民东路1588号","tel":"24060670"}]
     */

    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        private String title;
        /**
         * name : 文保分局沪东高校派出所
         * addr : 中山北一路801号
         * tel : 22027732
         */

        private List<ListBean> list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String name;
            private String addr;
            private String tel;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }
        }
    }
}
