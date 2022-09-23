import {NextApiRequest, NextApiResponse} from 'next'

export default function handler(req: NextApiRequest, res: NextApiResponse) {
  return res
    .status(400)
    .json({
      url: '/Users/jeonghwanmin/ml/seb39_main_012/client/public/138834243-fb74d81e-e90d-4c6a-8793-05df588f59ab.png',
    })
}
