package com.example.trafficts.Bean;

import java.util.List;

public class IDJson {

    /**
     * type : FeatureCollection
     * name : dl2
     * crs : {"type":"name","properties":{"name":"urn:ogc:def:crs:OGC:1.3:CRS84"}}
     * features : [{"type":"Feature","properties":{},"geometry":{"type":"MultiLineString","coordinates":[[[120.17219180819616,30.247646581986757],[120.17228048718609,30.24611505821963],[120.17238640035468,30.242809425005387]]]}},{"type":"Feature","properties":{},"geometry":{"type":"MultiLineString","coordinates":[[[120.17238640035468,30.242809425005387],[120.17239488314593,30.24228331680259],[120.17241093604004,30.241526223153574],[120.17241491917004,30.240976604091866],[120.17241749284332,30.24063268589661]]]}},{"type":"Feature","properties":{},"geometry":{"type":"MultiLineString","coordinates":[[[120.17227956192133,30.24285932600405],[120.17231321820174,30.24112124829641],[120.17230743305134,30.24071208332087]]]}},{"type":"Feature","properties":{},"geometry":{"type":"MultiLineString","coordinates":[[[120.1739209231843,30.215948913030534],[120.1744764781869,30.21507703575814],[120.17476650825955,30.214617510963745],[120.17503611333755,30.214176465685572],[120.17524061930355,30.213852002082138]]]}}]
     */

    private String type;
    private String name;
    private CrsBean crs;
    private List<FeaturesBean> features;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CrsBean getCrs() {
        return crs;
    }

    public void setCrs(CrsBean crs) {
        this.crs = crs;
    }

    public List<FeaturesBean> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeaturesBean> features) {
        this.features = features;
    }

    public static class CrsBean {
        /**
         * type : name
         * properties : {"name":"urn:ogc:def:crs:OGC:1.3:CRS84"}
         */

        private String type;
        private PropertiesBean properties;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public PropertiesBean getProperties() {
            return properties;
        }

        public void setProperties(PropertiesBean properties) {
            this.properties = properties;
        }

        public static class PropertiesBean {
            /**
             * name : urn:ogc:def:crs:OGC:1.3:CRS84
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class FeaturesBean {
        /**
         * type : Feature
         * properties : {}
         * geometry : {"type":"MultiLineString","coordinates":[[[120.17219180819616,30.247646581986757],[120.17228048718609,30.24611505821963],[120.17238640035468,30.242809425005387]]]}
         */

        private String type;
        private PropertiesBeanX properties;
        private GeometryBean geometry;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public PropertiesBeanX getProperties() {
            return properties;
        }

        public void setProperties(PropertiesBeanX properties) {
            this.properties = properties;
        }

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public static class PropertiesBeanX {
        }

        public static class GeometryBean {
            /**
             * type : MultiLineString
             * coordinates : [[[120.17219180819616,30.247646581986757],[120.17228048718609,30.24611505821963],[120.17238640035468,30.242809425005387]]]
             */

            private String type;
            private List<List<List<Double>>> coordinates;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<List<List<Double>>> getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(List<List<List<Double>>> coordinates) {
                this.coordinates = coordinates;
            }
        }
    }
}
