import {NextApiRequest, NextApiResponse} from 'next'
import {db} from './db'

export default function handler(req: NextApiRequest, res: NextApiResponse) {
  const user = req.body

  const result = db.some((obj) => obj.email === user.email && obj.password === user.password)

  if (result) {
    return res.status(200).json({message: 'success'})
  }

  return res.status(400).json({message: 'fail'})
}
