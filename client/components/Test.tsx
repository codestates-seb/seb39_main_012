import React from "react";

interface Props {
  children: React.ReactNode;
}

function Test({ children }: Props) {
  return <div>{children}</div>;
}

export default Test;
