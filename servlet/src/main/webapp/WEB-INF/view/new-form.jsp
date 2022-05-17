<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!-- 예제에서는 상대경로를 사용했지만 실무에서는 절대경로를 쓰는 것이 좋다. -->
<form action="save" method="post">
    username: <input type="text" name="username" />
    age:      <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>

<!--
    WEB-INF 밑의 리소스는 직접적으로 요청이 불가능하다. (WAS 규칙)
        - 웹 브라우저에서 해당 경로를 입력해서 직접 요청이 불가능
-->