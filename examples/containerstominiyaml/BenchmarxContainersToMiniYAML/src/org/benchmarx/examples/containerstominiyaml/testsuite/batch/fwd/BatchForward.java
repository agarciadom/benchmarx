package org.benchmarx.examples.containerstominiyaml.testsuite.batch.fwd;

import org.benchmarx.BXTool;
import org.benchmarx.examples.containerstominiyaml.testsuite.ContainersToMiniYAMLTestCase;
import org.benchmarx.examples.containerstominiyaml.testsuite.Decisions;
import org.junit.Test;

import containers.Composition;
import containers.Container;
import containers.Image;
import containers.Volume;


public class BatchForward extends ContainersToMiniYAMLTestCase {

	public BatchForward(BXTool<Composition, miniyaml.Map, Decisions> tool) {
		super(tool);
	}
	
	/**
	 * <b>Test</b> for agreed upon starting state.<br/>
	 * <b>Expect</b> root elements of both source and target models.<br/>
	 * <b>Features</b>: fwd, fixed
	 */
	@Test
	public void testInitialiseSynchronisation()
	{
		// No precondition!
		//------------
		util.assertPostcondition("RootElementContainers", "RootElementMiniYAML");
	}

	@Test
	public void addImage() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> compositionsHelper.addImage(c, "test"));
		util.assertPostcondition("Post_AddImageContainers", "RootElementMiniYAML");
	}

	@Test
	public void addVolume() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> compositionsHelper.addVolume(c, "storage"));
		util.assertPostcondition("Post_AddVolumeContainers", "Post_AddVolumeMiniYAML");
	}

	@Test
	public void addContainerOneReplica() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> compositionsHelper.addContainer(c, "myservice", 1));
		util.assertPostcondition("Post_AddContainerOneReplicaContainers", "Post_AddContainerOneReplicaMiniYAML");
	}

	@Test
	public void addContainerTwoReplicas() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> compositionsHelper.addContainer(c, "myservice", 2));
		util.assertPostcondition("Post_AddContainerTwoReplicasContainers", "Post_AddContainerTwoReplicasMiniYAML");
	}

	@Test
	public void addContainerWithImage() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> {
			Image image = compositionsHelper.addImage(c, "my/image");
			Container container = compositionsHelper.addContainer(c, "webserver", 1);
			container.setImage(image);
		});
		util.assertPostcondition("Post_AddContainerWithImageContainers", "Post_AddContainerWithImageMiniYAML");
	}

	@Test
	public void addContainerDependsOn() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> {
			Container cWebServer = compositionsHelper.addContainer(c, "webserver", 1);
			Container cDatabase = compositionsHelper.addContainer(c, "database", 1);
			cWebServer.getDependsOn().add(cDatabase);
		});
		util.assertPostcondition("Post_AddContainerDependsOnContainers", "Post_AddContainerDependsOnMiniYAML");
	}

	@Test
	public void addContainerVolumeMount() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> {
			Container container = compositionsHelper.addContainer(c, "database", 1);
			Volume volume = compositionsHelper.addVolume(c, "db_storage");
			compositionsHelper.mountVolume(container, volume, "/db/storage");
		});
		util.assertPostcondition("Post_AddContainerVolumeMountContainers", "Post_AddContainerVolumeMountMiniYAML");
	}

	/*
	 * The exact order of the options of a service was not properly defined in the
	 * case description, which is an issue when creating a new MiniYAML from scratch.
	 * This test should not be used to compare tools for the "exact order" case.
	 */
	@Test
	public void completeModel() {
		util.assertPrecondition("RootElementContainers", "RootElementMiniYAML");
		tool.performAndPropagateSourceEdit((c) -> {
			Container cWebServer = compositionsHelper.addContainer(c, "webserver", 2);
			cWebServer.setImage(compositionsHelper.addImage(c, "nginx:latest"));

			Container cDatabase = compositionsHelper.addContainer(c, "database", 1);
			cDatabase.setImage(compositionsHelper.addImage(c, "mariadb:latest"));
			cWebServer.getDependsOn().add(cDatabase);

			Volume volume = compositionsHelper.addVolume(c, "db_storage");
			compositionsHelper.mountVolume(cDatabase, volume, "/db/storage");
		});
		util.assertPostcondition("Post_CompleteModelContainers", "Post_CompleteModelMiniYAML");
	}
}
