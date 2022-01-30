package com.example.discount.Model;

import android.text.TextUtils;
import android.util.Patterns;
    public class User implements IUser{
        private  String email,discount;
        public User(String email, String discount) {
            this.email = email;
            this.discount = discount;
        }
        @Override
        public String getEmail() {
            return email;
        }
        @Override
        public String getDiscount() {
            return discount;
        }
        @Override
        public int isValid() {

            // 1. Check for Email Match pattern

            if(TextUtils.isEmpty(getEmail()))
                return  0;
            else if(!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
                return  1;
            else if(TextUtils.isEmpty(getDiscount()))
                return 2;
            else if(getDiscount().length()<=3)
                return 3;
            else if(getDiscount().length()<=4)
                return 4;
            else if(getDiscount().length()<=5)
                return 5;
            else if(getDiscount().length()<=3)
                return 3;

            else
                return -1;

        }
    }

