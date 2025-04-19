package com.nice.avishkar;

import java.nio.file.Path;

public class ResourceInfo {
    Path transportSchedulePath;
    Path customerRequestPath;

    public ResourceInfo(Path transportSchedulePath, Path customerRequestPath) {
        this.transportSchedulePath = transportSchedulePath;
        this.customerRequestPath = customerRequestPath;
    }
    public Path getTransportSchedulePath() {
        return transportSchedulePath;
    }

    public void setTransportSchedulePath(Path transportSchedulePath) {
        this.transportSchedulePath = transportSchedulePath;
    }

    public Path getCustomerRequestPath() {
        return customerRequestPath;
    }

    public void setCustomerRequestPath(Path customerRequestPath) {
        this.customerRequestPath = customerRequestPath;
    }
}
