<?php
   session_start();

$usuario_valido = "admin";
$clave_valida = "12345";


if (isset($_SESSION['usuario'])) {
    echo "BIENVENIDO " . $_SESSION['usuario'];
} 

elseif (isset($_COOKIE['usuario'])) {
    $_SESSION['usuario'] = $_COOKIE['usuario'];
    echo "BIENVENIDO " . $_SESSION['usuario'];
} 

elseif (isset($_POST['usuario']) && isset($_POST['contraseña'])) {
    $usuario = $_POST['usuario'];
    $clave = $_POST['contraseña'];
    $recordar = isset($_POST['recordar']);

    if ($usuario == $usuario_valido && $clave == $clave_valida) {
        $_SESSION['usuario'] = $usuario;

        if ($recordar) {
            setcookie("usuario", $usuario, time() + (86400 * 7), "/");
        } else {
            setcookie("usuario", "", time() - 3600, "/");
        }

        echo "BIENVENIDO " . $usuario;
    } else {
        header("Location: error.php");
        exit;
    }
} 

else {
    header("Location: error.php");
    exit;
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio</title>
</head>
<body>
    <br>
    <a href="index.html"><button>Cerrar sesión</button></a>
</body>
</html>
