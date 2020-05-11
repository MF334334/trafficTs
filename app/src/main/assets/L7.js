var linearScene = new L7.Scene({
	id: 'linearMap',
	mapStyle: 'dark', // 样式URL·
	center: [120.17503611333755,
		30.214176465685572
	],
	pitch: 20,
	zoom: 16,
	zoomControl: false,

});

var heatScene = new L7.Scene({
    id: 'heatMap',
    mapStyle: 'light', // 样式URL
    center: [116.49434030056, 39.868073421167621],
    pitch: 0,
    zoom: 16
});

var dataHeat = JSON.stringify({
	"type": "FeatureCollection",
	"features": [{
			"type": "Feature",
			"geometry": {
				"type": "Point",
				"coordinates": [111.358881, 40.195233]
			},
			"properties": {
				"annualCarbon": 29.15,
				"capacity": 6720,
				"coalType": "Bituminous",
				"country": "China",
				"plant": "Datang Tuoketuo power station",
				"status": "Operating",
				"type": "Subcritical",
				"retire1": 2100,
				"retire2": 2100,
				"retire3": 2100,
				"start1": 2003,
				"start2": 2017,
				"year1": 2017,
				"year2": 2019,
				"startLabel": "2003 - 2017",
				"regionLabel": "China"
			}
		},
		{
			"type": "Feature",
			"geometry": {
				"type": "Point",
				"coordinates": [34.200134, 26.250037]
			},
			"properties": {
				"annualCarbon": 25.6,
				"capacity": 6600,
				"coalType": "Unknown",
				"country": "Egypt",
				"plant": "Hamarawein IPP coal project",
				"status": "Announced",
				"type": "Ultra-super",
				"retire1": 2100,
				"retire2": 2100,
				"retire3": null,
				"start1": 2100,
				"start2": 2100,
				"year1": 2019,
				"year2": 2019,
				"startLabel": "?",
				"regionLabel": "Africa and Middle East"
			}
		},

		{
			"type": "Feature",
			"geometry": {
				"type": "Point",
				"coordinates": [79.29, 20.0063]
			},
			"properties": {
				"annualCarbon": 13.7,
				"capacity": 2920,
				"coalType": "Bituminous",
				"country": "India",
				"plant": "Chandrapur Thermal Power Station",
				"status": "Operating",
				"type": "Subcritical",
				"retire1": 2100,
				"retire2": 2100,
				"retire3": 2100,
				"start1": 1985,
				"start2": 2016,
				"year1": 2016,
				"year2": 2019,
				"startLabel": "1985 - 2016",
				"regionLabel": "India"
			}
		},
		{
			"type": "Feature",
			"geometry": {
				"type": "Point",
				"coordinates": [21.461976, 51.667025]
			},
			"properties": {
				"annualCarbon": 16.1,
				"capacity": 2919,
				"coalType": "Bituminous",
				"country": "Poland",
				"plant": "Kozienice power station",
				"status": "Operating",
				"type": "Subcritical",
				"retire1": 2100,
				"retire2": 2100,
				"retire3": 2100,
				"start1": 1972,
				"start2": 1979,
				"year1": 2000,
				"year2": 2019,
				"startLabel": "1972 - 1979",
				"regionLabel": "EU28"
			}
		},
		{
			"type": "Feature",
			"geometry": {
				"type": "Point",
				"coordinates": [111.027436, 34.685518]
			},
			"properties": {
				"annualCarbon": 12.54,
				"capacity": 2900,
				"coalType": "Bituminous",
				"country": "China",
				"plant": "Datang Sanmenxia power station",
				"status": "Operating",
				"type": "Subcritical",
				"retire1": 2100,
				"retire2": 2100,
				"retire3": 2100,
				"start1": 1995,
				"start2": 2017,
				"year1": 2017,
				"year2": 2019,
				"startLabel": "1995 - 2017",
				"regionLabel": "China"
			}
		},
		{
			"type": "Feature",
			"geometry": {
				"type": "Point",
				"coordinates": [81.646357, 21.391385]
			},
			"properties": {
				"annualCarbon": 0.17,
				"capacity": 30,
				"coalType": "Unknown",
				"country": "India",
				"plant": "Siltara Sks power station",
				"status": "Operating",
				"type": "Subcritical",
				"retire1": 2100,
				"retire2": 2100,
				"retire3": 2100,
				"start1": 1900,
				"start2": 1900,
				"year1": 2000,
				"year2": 2019,
				"startLabel": "?",
				"regionLabel": "India"
			}
		}

	]
})

var dataLine = JSON.stringify({
	"type": "FeatureCollection",
	"name": "dl2",
	"crs": {
		"type": "name",
		"properties": {
			"name": "urn:ogc:def:crs:OGC:1.3:CRS84"
		}
	},
	"features": [{
			"type": "Feature",
			"properties": {},
			"geometry": {
				"type": "MultiLineString",
				"coordinates": [
					[
						[
							120.17219180819616,
							30.247646581986757
						],
						[
							120.17228048718609,
							30.24611505821963
						],
						[
							120.17238640035468,
							30.242809425005387
						]
					]
				]
			}
		},
		{
			"type": "Feature",
			"properties": {},
			"geometry": {
				"type": "MultiLineString",
				"coordinates": [
					[
						[
							120.17238640035468,
							30.242809425005387
						],
						[
							120.17239488314593,
							30.24228331680259
						],
						[
							120.17241093604004,
							30.241526223153574
						],
						[
							120.17241491917004,
							30.240976604091866
						],
						[
							120.17241749284332,
							30.24063268589661
						]
					]
				]
			}
		},
		{
			"type": "Feature",
			"properties": {},
			"geometry": {
				"type": "MultiLineString",
				"coordinates": [
					[
						[
							120.17227956192133,
							30.24285932600405
						],
						[
							120.17231321820174,
							30.24112124829641
						],
						[
							120.17230743305134,
							30.24071208332087
						]
					]
				]
			}
		},
		{
			"type": "Feature",
			"properties": {},
			"geometry": {
				"type": "MultiLineString",
				"coordinates": [
					[
						[
							120.1739209231843,
							30.215948913030534
						],
						[
							120.1744764781869,
							30.21507703575814
						],
						[
							120.17476650825955,
							30.214617510963745
						],
						[
							120.17503611333755,
							30.214176465685572
						],
						[
							120.17524061930355,
							30.213852002082138
						]
					]
				]
			}
		}
	]
});

linearScene.on('loaded', function() {
	//   $.get('http://192.168.0.105:8081/JSON', function(data) {
	// console.log("*****"+data)
	// console.log("*****"+JSON.parse(dataLine))
	scene.LineLayer({
		zIndex: 2
	}).source(JSON.parse(dataLine)).size(1).shape('line').color('#aaff00').animate({
		enable: true,
		interval: 0.4,
		duration: 1,
		trailLength: 0.8
	}).render();
	// });
});
 
heatScene.on('loaded', function() {
    $.get('https://gw.alipayobjects.com/os/basement_prod/c3f8bda2-081b-449d-aa9f-9413b779205b.json', function(data) {
      scene.HeatmapLayer({
          zIndex: 2
        }).source(data, {
          parser: {
            type: 'json',
            x: 'lng',
            y: 'lat'
          }
        }).size('count', [0, 1]) // weight映射通道
        .style({
          intensity: 10,
          radius: 20,
          opacity: 1,
          rampColors: {
            colors: ['#2E8AE6', '#69D1AB', '#DAF291', '#FFD591', '#FF7A45', '#CF1D49'],
            positions: [0, 0.2, 0.4, 0.6, 0.8, 1.0]
          }
        }).render();
    });
  });

function toggle(){
	$("#heatMap").hide();
}


function load() {
alert("21")

	scene.on('loaded', function() {
		//   $.get('http://192.168.0.105:8081/JSON', function(data) {
		// console.log("*****"+data)
		// console.log("*****"+JSON.parse(dataLine))
		scene.LineLayer({
			zIndex: 2
		}).source(JSON.parse(dataLine)).size(1).shape('line').color('#ff893a').animate({
			enable: true,
			interval: 0.4,
			duration: 1,
			trailLength: 0.8
		}).render();
		// });
	});
	
}

// 加载图层需要sceneload完成之后


function loaddata(data) {
	// 加载图层需要sceneload完成之后
	scene.on('loaded', function() {
		//   $.get('http://192.168.0.105:8081/JSON', function(data) {
		// console.log("*****"+data)
		// console.log("*****"+JSON.parse(data1))
		scene.LineLayer({
			zIndex: 2
		}).source(JSON.parse(data)).size(1).shape('line').color('#ff893a').animate({
			enable: true,
			interval: 0.4,
			duration: 1,
			trailLength: 0.8
		}).render();
		// });
	});
}

function qiehuan() {
	alert("切换了");
	scene.on('loaded', function() {
		https: //gw.alipayobjects.com/os/basement_prod/c3f8bda2-081b-449d-aa9f-9413b779205b.json

			$.get('https://gw.alipayobjects.com/os/basement_prod/c3f8bda2-081b-449d-aa9f-9413b779205b.json', function(data) {
				scene.HeatmapLayer({
						zIndex: 2
					}).source(data, {
						parser: {
							type: 'json',
							x: 'lng',
							y: 'lat'
						}
					}).size('count', [0, 1]) // weight映射通道
					.style({
						intensity: 10,
						radius: 20,
						opacity: 1,
						rampColors: {
							colors: ['#2E8AE6', '#69D1AB', '#DAF291', '#FFD591', '#FF7A45', '#CF1D49'],
							positions: [0, 0.2, 0.4, 0.6, 0.8, 1.0]
						}
					}).render();
			});
	});

}
