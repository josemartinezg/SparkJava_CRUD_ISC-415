<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${titulo}</title>'
    <!-- Fuentes e iconografía -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <!-- Hoja de estilo de bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Hoja de estilo (CSS) Original -->
    <link rel="stylesheet" href="css/generalStyle.css">
    <script src="js/mainScript.js"></script>
</head>
<body>
<!-- Edit Modal HTML -->
<div id="addEmployeeModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/agregarEstudiante" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Agregar Estudiante</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Matricula</label>
                        <input type="number" name="matricula" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" name="nombre" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Apellido</label>
                        <input type="text" name="apellido" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Telefono</label>
                        <input type="text" name="telefono" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <input onclick="window.location.href='/listadoDeEstudiantes'" type="button" class="btn btn-default" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Agregar" name="agregar">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
