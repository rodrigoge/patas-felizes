import { IconType } from "react-icons";
import "./styles.scss";

type Props = {
  label: string;
  Icon: IconType;
};

export default function Tag({ label, Icon }: Props) {
  return (
    <button className="tag-container">
      <Icon />
      <div>{label}</div>
    </button>
  );
}
