package com.example.demoWeb.domain;

import java.io.Serializable;

public class ResponBean {
        private boolean loginFlag;
        private String severMsg;

    public boolean isLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getSeverMsg() {
        return severMsg;
    }

    public void setSeverMsg(String severMsg) {
        this.severMsg = severMsg;
    }
}
