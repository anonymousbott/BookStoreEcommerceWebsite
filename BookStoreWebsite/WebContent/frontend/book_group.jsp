<div class="book">
				
				<div>
					<a href="view_book?id=${book.bookId }">
						<img class="book_small" alt="" src="data:image/jpg;base64,${book.base64Image}">
					</a>
				</div>
				 <div>
				 	<a href="view_book?id=${book.bookId}">
				 		<b>${book.title}</b>
				 	</a>
				 </div>
				 <div>
				 	<jsp:directive.include file="book_rating.jsp"/>
				 </div>
				<div><i>${book.author}</i></div>
				<div>$${book.price}</div>
				
			</div>
