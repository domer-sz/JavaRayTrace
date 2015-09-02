/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import pl.umk.raytracking.geometry.GeometricObject;
import pl.umk.raytracking.geometry.Sphere;
import pl.umk.raytracking.light.Light;
import pl.umk.raytracking.light.Source;
import pl.umk.raytracking.projection.Perspective;
import pl.umk.raytracking.utility.Color;
import pl.umk.raytracking.utility.Point2D;

import pl.umk.raytracking.utility.Ray;
import pl.umk.raytracking.utility.Vector3D;

/**
 *
 * @author Szymon
 */
public class Tracer {

    public void trace(int x, int y) throws IOException {
        //double ambientLight = 0.2;
       // double accurity = 0.000001;

        //double min = Double.MAX_VALUE;
        Color color = new Color(0.0F, 0.0F, 0.0F, 0);
        boolean hit = false;
       // boolean hit2 = false;
        Color tempColor2 = Driver.world.background;
        for (int row = 0; row < Driver.sampler.samples; row++) {
            for (int col = 0; col < Driver.sampler.samples; col++) {
                Point2D point = Driver.sampler.sample(row, col, x, y);
                Ray camRay = Driver.projection.createRay(point);
              //  Ray lightRay = Driver.lightProjection.createRay(point);

                double min = Double.MAX_VALUE;
                //double min2 = Double.MAX_VALUE;
                Color tempColor = Driver.world.background;

                
                List<Double> intersections = new ArrayList<>();
                for(int i = 0 ; i < Driver.world.objects.size(); i++){
                    intersections.add(Driver.world.objects.get(i).hit(camRay));
                    //System.out.println(Driver.world.objects.get(i).getColor().getSpecial());;
                }
                int winningObjIndex = getWinningObj(intersections);
                if (winningObjIndex == -1) {
                    tempColor = Driver.world.background;
                }else{
                     if (intersections.get(winningObjIndex) > Driver.accuracy) {
                         Vector3D intersection_position = camRay.origin.vectAdd(camRay.direction.vectMult(intersections.get(winningObjIndex)));
                         Vector3D intersecting_ray_direction = camRay.direction;
                         tempColor = getColorAt(intersection_position, intersecting_ray_direction, Driver.world.objects, winningObjIndex, Driver.world.lightSources);
                         hit=true;
                     }
                }
                
                
//for (GeometricObject object : Driver.world.objects) {
//                for(int winningIndex = 0 ; winningIndex < Driver.world.objects.size(); winningIndex++){
//                    double temp = Driver.world.objects.get(winningIndex).hit(camRay);
//                    if (temp != 0.0 && temp < min) {
//                        min = temp;
//                        hit = true;
//                        Vector3D intersection_position = camRay.origin.vectAdd(camRay.direction.vectMult(temp));
//                        Vector3D intersecting_ray_direction = camRay.direction;
//                        
//                        List<Double> intersections = new ArrayList<>();
//                        tempColor = getColorAt(intersection_position, intersecting_ray_direction, Driver.world.objects, winningIndex, Driver.world.lightSources);
//                        //tempColor = object.color;
//                     
//                    }
//
//                }
                color.add(tempColor);
            }
        }
        color.devide(Driver.sampler.samples * Driver.sampler.samples);

        if (hit) {
            Driver.myImage.buffer.setRGB(x, Driver.world.viewPlane.height - y - 1, color.toInteger());
        } else {
            Driver.myImage.buffer.setRGB(x, Driver.world.viewPlane.height - y - 1, Driver.world.background.toInteger());
        }

    }

    private static int getWinningObj(List<Double> intersections) {
        // return the index of the winning intersection
        int index_of_minimum_value = 0;

        // prevent unnessary calculations
        if (intersections.isEmpty()) {
            // if there are no intersections
            return -1;
        } else if (intersections.size() == 1) {
            if (intersections.get(0) > 0) {
                // if that intersection is greater than zero then its our index of minimum value
                return 0;
            } else {
                // otherwise the only intersection value is negative
                return -1;
            }
        } else {
            // otherwise there is more than one intersection
            // first find the maximum value

            double max = 0;
            for (int i = 0; i < intersections.size(); i++) {
                if (max < intersections.get(i)) {
                    max = intersections.get(i);
                }
            }

            // then starting from the maximum value find the minimum positive value
            if (max > 0) {
                // we only want positive intersections
                for (int index = 0; index < intersections.size(); index++) {
                    if (intersections.get(index) > 0 && intersections.get(index) <= max) {
                        max = intersections.get(index);
                        index_of_minimum_value = index;
                    }
                }

                return index_of_minimum_value;
            } else {
                // all the intersections were negative
                return -1;
            }
        }
    }

    public static Color getColorAt(Vector3D intersection_position, Vector3D intersecting_ray_direction,
            List<GeometricObject> scene_objects, int index_of_winning_object, List<Source> lightSources) {
      //  System.out.println(index_of_winning_object);
        Color winning_object_color = scene_objects.get(index_of_winning_object).getColor();
        Vector3D winning_object_normal = scene_objects.get(index_of_winning_object).getNormalAt(intersection_position);
        
       
//        if (winning_object_color.s == 2) {
//            //System.out.println("robie podloge");
//		// checkered/tile floor pattern
//
//            int square = (int) Math.floor(intersection_position.getX()) + (int) Math.floor(intersection_position.getZ());
//
//            if ((square % 100) == 0) {
//
//                winning_object_color.setRed(0);
//                winning_object_color.setGreen(0);
//                winning_object_color.setBlue(0);
//            } else {
//
//                winning_object_color.setRed(1);
//                winning_object_color.setGreen(1);
//                winning_object_color.setRed(1);
//            }
//        }

        Color final_color = winning_object_color.colorScalar(Driver.ambientlight);

        if (winning_object_color.getSpecial() > 0 && winning_object_color.getSpecial() <= 1) {

            double dot1 = winning_object_normal.dotProduct(intersecting_ray_direction.negative());
            Vector3D scalar1 = winning_object_normal.vectMult(dot1);
            Vector3D add1 = scalar1.vectAdd(intersecting_ray_direction);
            Vector3D scalar2 = add1.vectMult(2);
            Vector3D add2 = intersecting_ray_direction.negative().vectAdd(scalar2);
            Vector3D reflection_direction = add2.normalizeout();

            Ray reflection_ray = new Ray(intersection_position, reflection_direction);

            List<Double> reflection_intersections = new ArrayList<>();

            for (int reflection_index = 0; reflection_index < scene_objects.size(); reflection_index++) {
                reflection_intersections.add(scene_objects.get(reflection_index).findIntersection(reflection_ray));
            }

            int index_of_winning_object_with_reflection = getWinningObj(reflection_intersections);

            if (index_of_winning_object_with_reflection != -1) {

                if (reflection_intersections.get(index_of_winning_object_with_reflection) > Driver.accuracy) {

                    Vector3D reflection_intersection_position = intersection_position.vectAdd(reflection_direction.vectMult(reflection_intersections.get(index_of_winning_object_with_reflection)));
                    Vector3D reflection_intersection_ray_direction = reflection_direction;

                    Color reflection_intersection_color = getColorAt(reflection_intersection_position, reflection_intersection_ray_direction, scene_objects, index_of_winning_object_with_reflection, lightSources);

                    final_color = final_color.colorAdd(reflection_intersection_color.colorScalar(winning_object_color.getSpecial()));
                }
            }
        }

        for (int light_index = 0; light_index < lightSources.size(); light_index++) {
            Vector3D light_direction = lightSources.get(light_index).getPosition().vectAdd(intersection_position.negative()).normalizeout();

            double cosine_angle = winning_object_normal.dotProduct(light_direction);

            if (cosine_angle > 0) {
                // test for shadows
                boolean shadowed = false;

                Vector3D distance_to_light = lightSources.get(light_index).getPosition().vectAdd(intersection_position.negative()).normalizeout();
                float distance_to_light_magnitude = (float) distance_to_light.magnitude();

                Ray shadow_ray = new Ray(intersection_position, lightSources.get(light_index).getPosition().vectAdd(intersection_position.negative()).normalizeout());

                List<Double> secondary_intersections = new ArrayList<>();

                for (int object_index = 0; object_index < scene_objects.size() && shadowed == false; object_index++) {
                    secondary_intersections.add(scene_objects.get(object_index).findIntersection(shadow_ray));
                }

                for (int c = 0; c < secondary_intersections.size(); c++) {
                    if (secondary_intersections.get(c) > Driver.accuracy) {
                        if (secondary_intersections.get(c) <= distance_to_light_magnitude) {
                            shadowed = true;
                        }
                    }
                    break;
                }

                if (shadowed == false) {
                    final_color = final_color.colorAdd(winning_object_color.colorMultiply(lightSources.get(light_index).getColor()).colorScalar(cosine_angle));

                    if (winning_object_color.getSpecial() > 0 && winning_object_color.getSpecial() <= 1) {
                        // special [0-1]
                        double dot1 = winning_object_normal.dotProduct(intersecting_ray_direction.negative());
                        Vector3D scalar1 = winning_object_normal.vectMult(dot1);
                        Vector3D add1 = scalar1.vectAdd(intersecting_ray_direction);
                        Vector3D scalar2 = add1.vectMult(2);
                        Vector3D add2 = intersecting_ray_direction.negative().vectAdd(scalar2);
                        Vector3D reflection_direction = add2.normalizeout();

                        double specular = reflection_direction.dotProduct(light_direction);
                        if (specular > 0) {
                            specular = Math.pow(specular, 10);
                            final_color = final_color.colorAdd(lightSources.get(light_index).getColor().colorScalar(specular * winning_object_color.getSpecial()));
                        }
                    }

                }

            }
        }

        return final_color.clip();
    }

}
