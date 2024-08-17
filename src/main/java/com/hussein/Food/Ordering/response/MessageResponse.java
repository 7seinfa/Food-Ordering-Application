package com.hussein.Food.Ordering.response;

import lombok.Data;

/**
 * This data structure contains a response the server can give to a request, consisting of only a message.
 * @author Hussein Abdallah
 */
@Data
public class MessageResponse {
    private String message;
}
