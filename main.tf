terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0.1"
    }
  }
}

provider "docker" {
  host = "npipe:////.//pipe//docker_engine"
}

resource "docker_image" "spring_image" {
  name         = "tpDevOps:latest"
  keep_locally = false
}

resource "docker_container" "tpDevOps" {
  image = docker_image.tpDevOps.image_id
  name  = "tpDevOps"
  ports {
    internal = 80
    external = 8003
  }
env = [
    "SPRING_PROFILES_ACTIVE=production",
  ]
  volumes {
    container_path = "/app/logs"
    host_path      = "/var/log/tpDevOps"
    read_only      = false
  }
}
