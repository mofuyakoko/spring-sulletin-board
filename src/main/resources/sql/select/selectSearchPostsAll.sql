SELECT
	POST_ID
	,USER_ID
	,POST_TEXT
	,POST_DATE
FROM 
	TR_POSTS
WHERE
	USER_ID LIKE '%' || ? || '%'
	AND POST_TEXT LIKE '%' || ? || '%'
ORDER BY
	POST_DATE DESC