package com.app.auction.exception_handler;

public class ApiException extends RuntimeException {

		public ApiException(String message) {
			super(message);

		}

		public ApiException() {
			super();

		}


}