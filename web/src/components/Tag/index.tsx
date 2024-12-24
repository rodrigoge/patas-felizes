import { IconType } from "react-icons";
import "./styles.scss";
import { MouseEventHandler } from "react";

type Props = {
  label: string;
  Icon: IconType;
  onClick: MouseEventHandler<HTMLButtonElement>;
};

export default function Tag({ label, Icon, onClick }: Props) {
  return (
    <button className="tag-container" onClick={onClick}>
      <Icon />
      <div>{label}</div>
    </button>
  );
}
