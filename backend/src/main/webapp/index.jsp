<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<title>Photo App</title>
<body>
<h1>Success!</h1>
<table>
<thead>
        <tr class="columns">
        	<th id="col1">Sequence</th>
            <th id="col2">Photo Id</th>
            <th id="col3">Width</th>
            <th id="col4">Height</th>
            <th id="col5">Liked</th>
            <th id="col6">Photographer Id</th>
        </tr>
</thead>
<c:forEach varStatus="loopCounter" items="${photos}"  var="photo" >
  <tr>
  	 <td>${loopCounter.count}</td>
     <td>
     		<c:out value="${photo.photo_id}" />
     </td>
	 <td><c:out value="${photo.width}"/></td>
	 <td><c:out value="${photo.height}"/></td>
	 <td><c:out value="${photo.liked}"/></td>
	 <td><c:out value="${photo.photographer_id}"/></td>
  </tr>
</c:forEach>
</table>
</body>
</html>