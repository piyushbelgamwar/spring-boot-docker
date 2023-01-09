# spring-boot-docker
	public static <T> Object restPostCall(String url, Object inputObj, Class<T> responseType) throws Exception{
		Gson gson = new Gson();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(gson.toJson(inputObj), requestHeaders);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		ResponseEntity<T> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity, responseType);
		System.out.println("**************HTTP ENTITY**********"+httpEntity.getBody());
		//System.out.println(" Response: " + gson.toJson(result.getBody()!=null?result.getBody():"No response available"));
		
		return result.getBody();
	}
