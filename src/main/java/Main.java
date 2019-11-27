import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
public class Main {
    static ArrayList<Estudiante> misEstudiantes;

    public static void main(String[] args){

        misEstudiantes = new ArrayList<Estudiante>();
            Configuration configuration=new Configuration(Configuration.getVersion());
            configuration.setClassForTemplateLoading(Main.class, "spark/templates");
            FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        misEstudiantes.add(new Estudiante(250, "Jose", "Martinez", "809-912-6000"));
        misEstudiantes.add(new Estudiante(550, "Saul", "Feliciano", "849-999-5557"));
        misEstudiantes.add(new Estudiante(550, "Miguel", "Moronta", "829-999-5557"));

        //port(1234);
        port(getHerokuAssignedPort());
        staticFiles.location("/public");

        /*
            Método para realizar visualizar el listado de los estudiantes registrado.
        */
        Spark.get("/listadoDeEstudiantes", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Listado de Estudiantes");
            attributes.put("misEstudiantes", misEstudiantes);
            return new ModelAndView(attributes, "tablaEstudiantes.ftl");
        }, freeMarkerEngine);
        /*
            Método para realizar el post del nuevo estudiante.
        */

        Spark.get("/formulario", (request, response) -> {
            //Mapa del título y otros posibles atributos.
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Agregar Estudiante");
            return new ModelAndView(attributes, "agregarEstudiante.ftl");
        }, freeMarkerEngine);
        /*
            Método para realizar el post del nuevo estudiante.
        */
        Spark.post("/agregarEstudiante", (request, response) -> {
            int matricula = Integer.parseInt(request.queryParams("matricula"));
            String nombre = request.queryParams("nombre");
            String apellido = request.queryParams("apellido");
            String telefono = request.queryParams("telefono");
            Estudiante estudiante = new Estudiante(matricula, nombre, apellido, telefono);

            misEstudiantes.add(estudiante);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Agregar Estudiante");
            attributes.put("estudiante", estudiante);
            //Devolvemos una plantilla "landing page" diciéndo que el estudiante fue agregado
            response.redirect("/listadoDeEstudiantes");
            return null;
        }, freeMarkerEngine);

        /*
            Método para editar un estudiante.
        */
        Spark.get("/editarEstudiante", (request, response) -> {
            Estudiante datosEstudiante = new Estudiante(0, "", "", "");
            Map<String, Object> attributes = new HashMap<>();
            for (Estudiante estudiante: misEstudiantes) {
                if(estudiante.getMatricula() == Integer.parseInt(request.queryParams("matricula"))) {
                    datosEstudiante = estudiante;
                }
            }
            attributes.put("title", "Editar Estudiante");
            attributes.put("estudiante", datosEstudiante);

            return new ModelAndView(attributes, "editarEstudiante.ftl");
        }, freeMarkerEngine);
        /*
            Método para editar un estudiante por parámetro.
        */
        Spark.post("/editarEstudiante/:matricula", (request, response) -> {
            int matricula = Integer.parseInt(request.params("matricula"));
            String nombre = request.queryParams("nombre");
            String apellido = request.queryParams("apellido");
            String telefono = request.queryParams("telefono");
            Estudiante estudiante = new Estudiante(matricula, nombre, apellido, telefono);
            int idx = 0;
            for (Estudiante estud: misEstudiantes) {
                if(estud.getMatricula() == matricula) {
                    misEstudiantes.set(idx, estudiante);
                    break;
                }
                idx++;
            }
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Listado de Estudiantes");
            attributes.put("estudiantes", misEstudiantes);

            response.redirect("/listadoDeEstudiantes");
            return null;
        }, freeMarkerEngine);
        /*
            Método para eliminar estudiantes por parametro.
        */
        Spark.get("/eliminar/:matricula", (request, response) -> {
            int matricula = Integer.parseInt(request.params("matricula"));
            for (Estudiante estudiante: misEstudiantes) {
                if(estudiante.getMatricula() == matricula) {
                    misEstudiantes.remove(estudiante);
                    break;
                }
            }
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Lista de Estudiantes");
            attributes.put("estudiantes", misEstudiantes);

            response.redirect("/listadoDeEstudiantes");
            return "";
        });
    }
    /**
     * Metodo para setear el puerto en Heroku
     * @return
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //Retorna el puerto por defecto en caso de no estar en Heroku.
    }

}

