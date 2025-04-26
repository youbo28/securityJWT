package com.razali.securityJWT.model;

import java.util.Date;

public record ErrorResponse (int statusCode, Date timeStamp, String message, String description) {
}
