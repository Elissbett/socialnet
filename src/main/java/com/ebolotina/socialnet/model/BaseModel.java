package com.ebolotina.socialnet.model;

import java.util.Date;

public class BaseModel {
    private Long id;
    private Date createdDate;

    public static class BaseBuilder{

        Date createdDate;

        public BaseBuilder withCreatedDate(Date date){
            this.createdDate = date;
            return this;
        }
    }
}
