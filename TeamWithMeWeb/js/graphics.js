
$(document).ready(function() {

    // Get WebGL container
    container = $('#webGL');

    var canvasHeight = 450;

    // Create renderer and add it to the container
    var renderer = new THREE.WebGLRenderer({ antialias: true });
    renderer.setSize(container.innerWidth(), canvasHeight);
    document.getElementById('webGL').appendChild(renderer.domElement);

    window.addEventListener('resize', onWindowResize, false);

    // Create scene
    var scene = new THREE.Scene();

    // Create camera and add it to the main scene
    var frustumFar = 4000;
    var fov = container.innerWidth() / canvasHeight;
    var camera = new THREE.PerspectiveCamera(45, fov, 1, frustumFar);
    scene.add(camera);

    // Create skybox
    var segments = 16;
    var radius = 20;
    var skyGeometry = new THREE.SphereGeometry(radius, segments, segments);
    var skyMaterial = new THREE.MeshBasicMaterial({ side: THREE.BackSide, color: 0xffffff });
    var skyMesh = new THREE.Mesh(skyGeometry, skyMaterial);
    scene.add(skyMesh);
    
    // Create hemisphere light
    var h_Light = new THREE.HemisphereLight(0xffffff, 0xffffff, 1);
    scene.add(h_Light);

    // Create ambient light
    // var a_light = new THREE.AmbientLight(0xffffff);
    // scene.add(a_light);

    // Create directional light
    var d_light = new THREE.DirectionalLight(0xffffff, 0.3);
    d_light.position.set(0, 1, 1);
    scene.add(d_light);

    // Initialize camera position
    camera.position.z = 10;

    buildNodes();

    run();

    function onWindowResize()
    {
        camera.aspect = container.innerWidth() / canvasHeight;
        camera.updateProjectionMatrix();
        renderer.setSize(container.innerWidth(), canvasHeight);
    }

    function buildNodes()
    {
        var nodeCount = 16;
        var nodeRadius = 0.15;
        var segments = 10;
        var graphRadius = 3.5;

        var geometry = new THREE.SphereGeometry(nodeRadius, segments, segments);
        var material = new THREE.MeshLambertMaterial({ color: 0x3399ff });
        var textMaterial = new THREE.MeshBasicMaterial({ color: 0x000000 });
        var lineMaterial = [
            material,
            new THREE.LineBasicMaterial({ color: 0xFF3333 })
        ];

        graph = new THREE.Object3D();
        nodeList = new THREE.Object3D();
        edgeList = new THREE.Object3D();
        textList = new THREE.Object3D();
        graph.add(nodeList);
        graph.add(edgeList);
        graph.add(textList);
        scene.add(graph);

        for (var i = 0; i < nodeCount; i++)
        {
            var theta = i * (2 * Math.PI / nodeCount);
            var node = new THREE.Mesh(geometry, material);
            node.position.x = Math.cos(theta) * graphRadius;
            node.position.y = Math.sin(theta) * graphRadius;
            nodeList.add(node);

            var textGeometry = new THREE.TextGeometry(userData[i].name, { font: 'optimer', weight: 'bold', height: 0.001, size: 0.2 });
            textMesh = new THREE.Mesh(textGeometry, textMaterial);
            textMesh.position.x = Math.cos(theta) * (graphRadius * 1.1);
            if (textMesh.position.x < 0)
            {
                textMesh.position.x -= (0.14 * userData[i].name.length);
            }
            textMesh.position.y = Math.sin(theta) * (graphRadius * 1.1);
            textList.add(textMesh);

            for (var j = 0; j < nodeList.children.length; j++)
            {
                if (Math.random() < 0.6)
                    continue;

                var lineGeometry = new THREE.Geometry();
                lineGeometry.vertices.push(node.position);
                lineGeometry.vertices.push(nodeList.children[j].position);

                var n = Math.floor(Math.random() * lineMaterial.length);
                
                var line = new THREE.Line(lineGeometry, lineMaterial[n]);
                edgeList.add(line);
            }
        }
    }

    function run()
    {
        // Render the scene
        renderer.render(scene, camera);

        // Request another frame
        // requestAnimationFrame(run);
    }
});
