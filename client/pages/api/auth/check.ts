import {NextApiRequest, NextApiResponse} from 'next'
import {db} from './db'

export default function handler(req: NextApiRequest, res: NextApiResponse) {
  const {email} = req.query

  const result = db.some((obj) => obj.email === email)

  res.status(200).json(result)
}
