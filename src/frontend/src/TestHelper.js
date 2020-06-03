import renderer from "react-test-renderer";

export function generateSnapshotTree(component) {
  const model = renderer.create(component);
  const tree = model.toJSON();

  return tree;
}
