Feature: Search weather in your city 

Scenario Outline: Search weather in your city 
	When User navigates to <website> 
	Then Verify that Search box is display 
	When User searches for keyword <keyword> 
	Then Verify that user should see <expected> 
	
	Examples: 
	
		| website | keyword | expected |
		| https://openweathermap.org/ | Ho Chi Minh city | Thanh pho Ho Chi Minh, VN |