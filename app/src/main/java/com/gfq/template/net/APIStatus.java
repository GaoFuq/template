package com.gfq.template.net;

public interface APIStatus {
    void onAPIStart();
    void onAPIComplete();
    void onAPIError();
}
