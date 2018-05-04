package com.prisma.deploy.database.schema.queries

import com.prisma.deploy.specutils.ActiveDeploySpecBase
import com.prisma.shared.models.ProjectId
import org.scalatest.{FlatSpec, Matchers}

class ClusterInfoSpec extends FlatSpec with Matchers with ActiveDeploySpecBase {

  "ClusterInfo query" should "return cluster version" in {
    val (project, _) = setupProject(basicTypesGql)
    val nameAndStage = testDependencies.projectIdEncoder.fromEncodedString(project.id)
    val result       = server.query(s"""
                                       |query {
                                       |  clusterInfo {
                                       |    version
                                       |  }
                                       |}
      """.stripMargin)

    result.pathAsString("data.clusterInfo.version") shouldEqual sys.env("CLUSTER_VERSION")
  }
}
