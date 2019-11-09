<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>

<body>

<%@include file="header.jsp"%>
<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-75 padding-small">

                <h1 class="text-color-darker text-center">O aplikacji</h1>

                <h3>Vestibulum ut sem eu urna aliquam mollis. Nulla facilisi. Nullam est augue, </h3>
                <h4>tincidunt in tortor at, iaculis placerat mauris. Nunc sodales, </h4>
                <h5>velit at accumsan laoreet, nulla erat posuere nisi, vel gravida elit nunc eu lorem. </h5>
                <h6>Duis fringilla mauris dolor, eget tempor leo consequat vel. Maecenas quis porttitor eros. <br/>
                    Sed gravida, neque in scelerisque pulvinar, sem mi ornare purus,<br/>
                    eget hendrerit turpis lorem quis magna.Suspendisse a magna dictum,<br/>
                    bibendum orci vel, auctor mauris. Sed ut orci orci. Proin eget turpis elit.<br/>
                    In hac habitasse platea dictumst. Donec diam.</h6>

            </div>
        </div>
    </div>
</section>


<%@include file="footer.jsp"%>

</body>
</html>