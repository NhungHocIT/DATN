package com.example.GoalMaster.response;

public class LoginResponse {
    private String message;
    private int statusCode;
    private boolean status;
    private UserData data;

    public LoginResponse() {
    }

    // getter và setter

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    /**
     * Class lồng bên trong chứa thông tin user trả về.
     */
    public static class UserData {
        private long id;
        private String emailPhone;
        private Profile profile;

        public UserData() {
        }

        // getter và setter
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getEmailPhone() {
            return emailPhone;
        }

        public void setEmailPhone(String emailPhone) {
            this.emailPhone = emailPhone;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }
    }

    /**
     * Class profile chi tiết trong user.
     */
    public static class Profile {
        private long id;
        private String avatar_url;
        private String background_image;
        private String bio;
        private String birth;
        private String username;

        public Profile() {
        }

        // getter và setter
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getBackground_image() {
            return background_image;
        }

        public void setBackground_image(String background_image) {
            this.background_image = background_image;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
