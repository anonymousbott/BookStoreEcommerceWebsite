<c:forTokens items="${book.ratingStars}" delims="," var="start">
				 		<c:if test="${start eq 'on' }">
				 			<img alt="" src="images/rating_on.png">
				 		</c:if>
				 		<c:if test="${start eq 'off' }">
				 			<img alt="" src="images/rating_off.png">
				 		</c:if>
				 		<c:if test="${start eq 'half' }">
				 			<img alt="" src="images/rating_half.png">
				 		</c:if>
				 	</c:forTokens>