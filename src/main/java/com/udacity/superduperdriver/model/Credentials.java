package com.udacity.superduperdriver.model;

public class Credentials {
    private Long credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private Long userId;

    public Credentials(Long credentialId, Long userId, String url, String username, String password, String key) {
        this.credentialId = credentialId;
        this.userId = userId;
        this.url = url;
        this.username = username;
        this.password = password;
        this.key = key;
    }

    public Credentials() {
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
