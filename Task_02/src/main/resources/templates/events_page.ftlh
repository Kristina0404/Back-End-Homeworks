<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Events</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>Event</th>
            <th>Start</th>
            <th>End</th>
        </tr>
        <#list eventsList as event>
            <tr>
                <td>${event.title}</td>
                <td>${event.startDate}</td>
                <td>${event.endDate}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>