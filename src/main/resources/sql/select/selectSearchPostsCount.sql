SELECT
	COUNT(*)
FROM 
	TR_POSTS
WHERE
	USER_ID LIKE '%' || ? || '%'
	AND POST_TEXT LIKE '%' || ? || '%'