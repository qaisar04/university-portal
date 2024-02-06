<!DOCTYPE html>
<html lang="ru">
<head>
    <title>${subject}</title>
    <style>
        /* Стили для заголовка */
        h1 {
            color: #333;
            text-align: center;
            font-family: Arial, sans-serif;
            text-transform: uppercase;
        }

        /* Стили для контейнера */
        .content {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
        }

        /* Стили для параграфа */
        p {
            color: #666;
            line-height: 1.6;
            font-size: 18px;
            font-family: Georgia, serif;
        }

        /* Стили для ссылки */
        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        /* Стили для кнопки */
        .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-transform: uppercase;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #0056b3;
        }

        body {
            background-color: #f0f0f0;
            font-family: Arial, sans-serif;
        }
    </style>
</head>
<body>
<div class="content">
    <h1>Добро пожаловать!</h1>
    <p>${content}</p>
</div>
</body>
</html>