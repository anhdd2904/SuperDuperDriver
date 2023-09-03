package com.udacity.superduperdriver.dto;

public class CredentialsDTO {
    private Long credentialId;
    private String url;
    private String username;
    private String credentialkey;
    private String password;
    private Long userId;
    private String decryptedPassword;

    public CredentialsDTO() {
    }

    public CredentialsDTO(Long credentialId, String url, String username, String key, String password, Long userId, String decryptedPassword) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.credentialkey = key;
        this.password = password;
        this.userId = userId;
        this.decryptedPassword = decryptedPassword;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCredentialKey() {
        return credentialkey;
    }

    public void setCredentialKey(String key) {
        this.credentialkey = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDecryptedPassword() {
        return decryptedPassword;
    }

    @Override
    public String toString() {
        return "CredentialsDTO{" +
                "credentialId=" + credentialId +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", key='" + credentialkey + '\'' +
                ", password='" + password + '\'' +
                ", userId=" + userId +
                ", decryptedPassword='" + decryptedPassword + '\'' +
                '}';
    }

    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }
}
